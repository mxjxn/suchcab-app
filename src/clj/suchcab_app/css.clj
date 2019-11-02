(ns suchcab-app.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "red"}
   [:.login-panel {:margin "auto 100px auto"
                   :padding "100px"}
    [:.login-card {:margin "10px"}]
    [:.login-card>* {:margin "0 10px"}]
    [:.submit-button {:margin "10px 0"}]
    [:.login-card-label {:margin "10px auto 0"
                         :text-align :center}]]])
