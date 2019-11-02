(ns suchcab-app.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [suchcab-app.db :as db]
   [ajax.core :as ajax]
   ))
(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(reg-event-fx
 :login-request/signup
 (fn [{:keys [db]} [_ email username password]]
   {:db db
    :http-xhrio {:method :post
                 :uri "http://localhost:3000/api/user/create"
                 :params {:email email :username username :password password}
                 :timeout 8000
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [:signup-success]
                 :on-failure [:signup-failure]}}))

(reg-event-fx
 :math-that-shit
 (fn [{:keys [db]} evt]
   {:db db
    :http-xhrio {:method :post
                 :uri "http://localhost:3000/api/math/plus"
                 :params {:x 1 :y 3}
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [:math-success]
                 :on-failure [:math-failure]}}))

(reg-event-db
 :math-failure
 (fn [db evt]
   (println "math failure")))

(reg-event-db
 :math-success
 (fn [db evt]
   (println "math success")))

(reg-event-db
 :signup-success
 (fn [db evt]
   (println "signup success...\n" evt)))

(reg-event-db
 :signup-failure
 (fn [db evt]
   (println "signup failure...\n" evt)))

(reg-event-fx
 :login-request/login
 (fn [{:keys [db]} [_ email password]]
   (println "logging in...\n" email password)
   {:db db
    :http-xhrio {:method :post
                 :uri "http://localhost:3000/api/user/login"
                 :params {:email email :password password}
                 :timeout 8000 ; 8 seconds
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [:login-success]
                 :on-failure [:login-failure]}}))

(reg-event-db
 :login-success
 (fn [db evt]
   (println "success...\n" evt)))

(reg-event-db
 :login-failure
 (fn [db evt]
   (println "failure...\n" evt)))
