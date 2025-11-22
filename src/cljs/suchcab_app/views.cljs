(ns suchcab-app.views
  (:require
   [re-frame.core :as rf]
   [reagent.core :as r]
   [suchcab-app.subs :as subs]
   [suchcab-app.components :refer
    [Container Button Card AppBar FormGroup
     Typography
     FormControl TextField Toolbar]]
   ))

;; Alert component for errors and success messages
(defn Alert [{:keys [severity message on-close]}]
  (when message
    [:div {:style {:padding "16px"
                   :margin "16px 0"
                   :border-radius "4px"
                   :background-color (if (= severity :error) "#f44336" "#4caf50")
                   :color "white"
                   :display "flex"
                   :justify-content "space-between"
                   :align-items "center"}}
     [:span message]
     (when on-close
       [:button {:on-click on-close
                 :style {:background "transparent"
                         :border "none"
                         :color "white"
                         :cursor "pointer"
                         :font-size "20px"}}
        "×"])]))

;; Login/Signup Panel
(defn LoginPanel []
  (let [creds (r/atom {:email "" :password ""})
        loading @(rf/subscribe [::subs/loading])
        error @(rf/subscribe [::subs/error])
        success @(rf/subscribe [::subs/success])
        form-errors @(rf/subscribe [::subs/form-errors])

        login-fn (fn [{:keys [email password]}]
                   (rf/dispatch [:login-request/login email password]))
        signup-fn (fn [{:keys [email password]}]
                    (rf/dispatch [:login-request/signup email password]))]
    [:div.login-panel
     [Container {:maxWidth "sm"}
      [Alert {:severity :error
              :message error
              :on-close #(rf/dispatch [:clear-messages])}]
      [Alert {:severity :success
              :message success
              :on-close #(rf/dispatch [:clear-messages])}]

      [Card {:className "login-card"
             :style {:padding "32px"
                     :margin-top "64px"}}
       [Typography {:className "login-card-label"
                    :variant :h5
                    :align "center"
                    :style {:margin-bottom "24px"}}
        "Welcome to Such.cab"]

       [:form {:name "login-form"
               :on-submit (fn [e]
                            (.preventDefault e)
                            (login-fn @creds))}
        [FormGroup
         [FormControl {:margin "normal" :fullWidth true}
          [TextField {:placeholder "Email address"
                      :variant :outlined
                      :fullWidth true
                      :type :email
                      :disabled loading
                      :error (boolean (:email form-errors))
                      :helperText (:email form-errors)
                      :on-change #(swap! creds assoc :email (-> % .-target .-value))
                      :name "email"}]]

         [FormControl {:margin "normal" :fullWidth true}
          [TextField {:type :password
                      :variant :outlined
                      :fullWidth true
                      :placeholder "Password"
                      :disabled loading
                      :error (boolean (:password form-errors))
                      :helperText (:password form-errors)
                      :name "password"
                      :on-change #(swap! creds assoc :password (-> % .-target .-value))
                      :on-key-press (fn [e]
                                      (when (= 13 (.-charCode e))
                                        (.preventDefault e)
                                        (login-fn @creds)))}]]

         [:div {:style {:margin-top "24px"
                        :display "flex"
                        :gap "16px"
                        :flex-direction "column"}}
          [Button
           {:className "submit-button"
            :variant "contained"
            :color "primary"
            :fullWidth true
            :disabled loading
            :on-click (fn [e]
                        (.preventDefault e)
                        (login-fn @creds))}
           (if loading "Logging in..." "Log In")]

          [Button
           {:className "signup-button"
            :variant "outlined"
            :color "primary"
            :fullWidth true
            :disabled loading
            :on-click (fn [e]
                        (.preventDefault e)
                        (signup-fn @creds))}
           (if loading "Signing up..." "Sign Up")]]]]]]]))

;; Dashboard View
(defn Dashboard []
  (let [user @(rf/subscribe [::subs/user])
        success @(rf/subscribe [::subs/success])]
    [:div
     [Container {:maxWidth "md" :style {:margin-top "32px"}}
      [Alert {:severity :success
              :message success
              :on-close #(rf/dispatch [:clear-messages])}]

      [Card {:style {:padding "32px"}}
       [Typography {:variant :h4 :style {:margin-bottom "16px"}}
        "Dashboard"]

       [Typography {:variant :body1 :style {:margin-bottom "24px"}}
        (str "Welcome, " (:email user) "!")]

       [Typography {:variant :body2 :color "textSecondary" :style {:margin-bottom "8px"}}
        "Account Details:"]

       [:div {:style {:background "#f5f5f5"
                      :padding "16px"
                      :border-radius "4px"
                      :margin-bottom "24px"}}
        [:p [:strong "Email: "] (:email user)]
        [:p [:strong "Account created: "] (str (:created-at user))]]

       [Button
        {:variant "outlined"
         :color "secondary"
         :on-click #(rf/dispatch [:logout])}
        "Log Out"]]]]))

;; Main Panel - Routes between views
(defn main-panel []
  (let [current-view @(rf/subscribe [::subs/current-view])]
    [:div
     [AppBar {:position "static" :color "primary"}
      [Toolbar
       [Typography {:variant :h6 :style {:flex-grow 1}}
        "Such.cab"]
       (when (= current-view :dashboard)
         [Button {:color "inherit"
                  :on-click #(rf/dispatch [:logout])}
          "Logout"])]]

     (case current-view
       :login [LoginPanel]
       :dashboard [Dashboard]
       [LoginPanel])]))

