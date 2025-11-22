(ns suchcab-app.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:margin 0
          :padding 0
          :font-family "-apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif"
          :background-color "#f5f5f5"
          :color "#333"}

   [:.login-panel {:min-height "100vh"
                   :display "flex"
                   :align-items "center"
                   :justify-content "center"
                   :padding "20px"}

    [:.login-card {:box-shadow "0 2px 8px rgba(0,0,0,0.1)"
                   :background-color "white"}

     [:.login-card-label {:color "#1976d2"
                          :font-weight "500"}]

     [:.submit-button {:margin-top "8px"}]
     [:.signup-button {:margin-top "8px"}]]]])
