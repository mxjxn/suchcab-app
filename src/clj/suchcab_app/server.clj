(ns suchcab-app.server
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response status]]
            [compojure.core :refer [defroutes POST GET]]
            [compojure.route :as route]
            [buddy.hashers :as hashers]))

;; In-memory user database (replace with real database in production)
(def users (atom {}))

;; Helper functions
(defn create-user! [email password]
  (let [user {:email email
              :password (hashers/derive password)
              :created-at (java.util.Date.)}]
    (swap! users assoc email user)
    (dissoc user :password)))

(defn authenticate-user [email password]
  (when-let [user (get @users email)]
    (when (hashers/check password (:password user))
      (dissoc user :password))))

(defn validate-email [email]
  (and email
       (string? email)
       (re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" email)))

(defn validate-password [password]
  (and password
       (string? password)
       (>= (count password) 6)))

;; API handlers
(defn create-user-handler [request]
  (let [{:keys [email password]} (:body request)]
    (cond
      (not (validate-email email))
      (-> (response {:error "Invalid email format"})
          (status 400))

      (not (validate-password password))
      (-> (response {:error "Password must be at least 6 characters"})
          (status 400))

      (get @users email)
      (-> (response {:error "User already exists"})
          (status 409))

      :else
      (let [user (create-user! email password)]
        (response {:success true
                   :user user
                   :message "User created successfully"})))))

(defn login-handler [request]
  (let [{:keys [email password]} (:body request)]
    (if-let [user (authenticate-user email password)]
      (response {:success true
                 :user user
                 :message "Login successful"})
      (-> (response {:error "Invalid email or password"})
          (status 401)))))

(defn math-plus-handler [request]
  (let [{:keys [x y]} (:body request)]
    (response {:result (+ x y)})))

;; Routes
(defroutes app-routes
  (POST "/api/user/create" [] create-user-handler)
  (POST "/api/user/login" [] login-handler)
  (POST "/api/math/plus" [] math-plus-handler)
  (GET "/api/health" [] (response {:status "ok"}))
  (route/not-found {:error "Not found"}))

;; Middleware
(def app
  (-> app-routes
      (wrap-json-body {:keywords? true})
      wrap-json-response
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :post :put :delete]
                 :access-control-allow-headers ["Content-Type"])))

;; Server
(defn -main [& args]
  (println "Starting server on port 3000...")
  (run-jetty app {:port 3000 :join? true}))
