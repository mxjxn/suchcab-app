(ns suchcab-app.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [suchcab-app.db :as db]
   [ajax.core :as ajax]))

;; Validation helpers
(defn validate-email [email]
  (and email
       (string? email)
       (re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" email)))

(defn validate-password [password]
  (and password
       (string? password)
       (>= (count password) 6)))

(defn validate-credentials [email password]
  (let [errors {}
        errors (if (validate-email email)
                 errors
                 (assoc errors :email "Invalid email format"))
        errors (if (validate-password password)
                 errors
                 (assoc errors :password "Password must be at least 6 characters"))]
    errors))

;; Initialize
(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

;; Clear messages
(reg-event-db
 :clear-messages
 (fn [db _]
   (assoc db :error nil :success nil)))

;; Logout
(reg-event-db
 :logout
 (fn [db _]
   (assoc db
          :current-view :login
          :user nil
          :error nil
          :success "Logged out successfully")))

;; Login
(reg-event-fx
 :login-request/login
 (fn [{:keys [db]} [_ email password]]
   (let [validation-errors (validate-credentials email password)]
     (if (empty? validation-errors)
       {:db (assoc db
                   :loading true
                   :error nil
                   :form-errors {})
        :http-xhrio {:method :post
                     :uri "http://localhost:3000/api/user/login"
                     :params {:email email :password password}
                     :timeout 8000
                     :format (ajax/json-request-format)
                     :response-format (ajax/json-response-format {:keywords? true})
                     :on-success [:login-success]
                     :on-failure [:login-failure]}}
       {:db (assoc db :form-errors validation-errors)}))))

(reg-event-db
 :login-success
 (fn [db [_ response]]
   (assoc db
          :loading false
          :current-view :dashboard
          :user (:user response)
          :success (:message response)
          :error nil
          :form-errors {})))

(reg-event-db
 :login-failure
 (fn [db [_ response]]
   (let [error-msg (or (get-in response [:response :error])
                       "Login failed. Please try again.")]
     (assoc db
            :loading false
            :error error-msg))))

;; Signup
(reg-event-fx
 :login-request/signup
 (fn [{:keys [db]} [_ email password]]
   (let [validation-errors (validate-credentials email password)]
     (if (empty? validation-errors)
       {:db (assoc db
                   :loading true
                   :error nil
                   :form-errors {})
        :http-xhrio {:method :post
                     :uri "http://localhost:3000/api/user/create"
                     :params {:email email :password password}
                     :timeout 8000
                     :format (ajax/json-request-format)
                     :response-format (ajax/json-response-format {:keywords? true})
                     :on-success [:signup-success]
                     :on-failure [:signup-failure]}}
       {:db (assoc db :form-errors validation-errors)}))))

(reg-event-db
 :signup-success
 (fn [db [_ response]]
   (assoc db
          :loading false
          :current-view :dashboard
          :user (:user response)
          :success (:message response)
          :error nil
          :form-errors {})))

(reg-event-db
 :signup-failure
 (fn [db [_ response]]
   (let [error-msg (or (get-in response [:response :error])
                       "Signup failed. Please try again.")]
     (assoc db
            :loading false
            :error error-msg))))
