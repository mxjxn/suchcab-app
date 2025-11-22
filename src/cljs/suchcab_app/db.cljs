(ns suchcab-app.db)

(def default-db
  {:current-view :login  ; :login or :dashboard
   :user nil             ; Current logged-in user
   :loading false        ; Global loading state
   :error nil            ; Error message to display
   :success nil          ; Success message to display
   :form-errors {}       ; Form validation errors {:email "..." :password "..."}
   })
