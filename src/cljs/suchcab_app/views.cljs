(ns suchcab-app.views
  (:require
   [re-frame.core :as rf]
   [reagent.core :as r]
   [suchcab-app.subs :as subs]
   [suchcab-app.components :refer
    [Container Button Menu Card AppBar FormGroup
     Typography
     FormControl TextField Toolbar Input InputLabel]]
   ))


(defn LoginPanel []
  (let [creds (r/atom {:email "" :password ""})
        math-fn (fn [_] (rf/dispatch [:math-that-shit]))
        signup-fn (fn [{:keys [email password]}]
                    (rf/dispatch [:login-request/signup email email password]))
        submit-fn (fn [{:keys [email password]}]
                    (rf/dispatch [:login-request/login email password]))]
    [:div.login-panel
     [Card {:className "login-card"}
      [Typography {:className "login-card-label" :variant :h5} "Log In or Sign Up"]
      [:form {:name "signup"}
       [FormGroup
        [FormControl {:margin "normal"}
         [TextField {:placeholder "email address"
                     :variant :outlined
                     :on-change #(swap! creds merge {:email (-> % .-target .-value)} )
                     :name "email"}]]
        [FormControl
         [TextField {:type :password
                     :variant :outlined
                     :placeholder "password"
                     :name "password"
                     :on-change #(swap! creds merge {:password (-> % .-target .-value)} )
                     :on-key-press (fn [e]
                                     (if (= 13 (.-charCode e))
                                       (submit-fn @creds)))}]]
        [Button
         {:className "submit-button"
          :color "secondary"
          :margin "10px"
          :on-click (fn [e]
                      (println "creds: " @creds)
                      (submit-fn @creds))}
         "Submit"]
        [Button
         {:className "math-button"
          :color "secondary"
          :margin "10px"
          :on-click (fn [e]
                      (println "creds: " @creds)
                      (math-fn @creds))}
         "Signup"]
        [Button
         {:className "submit-button"
          :color "secondary"
          :margin "10px"
          :on-click (fn [e]
                      (math-fn e))}
         "mathy"]
        ]]]]))

(defn main-panel []
  (let [name (rf/subscribe [::subs/name])]
    [:div
     [AppBar {:position "relative" :color "primary"}
      [Toolbar
       [Typography {:variant :h6} "Such.cab"]]]
     [LoginPanel ]
     ]))

