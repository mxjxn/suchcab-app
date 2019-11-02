(ns suchcab-app.components
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]))

(defn adapt [c] (r/adapt-react-class c))

(def Container (adapt m/Container))
(def AppBar (adapt m/AppBar))
(def Button (adapt m/Button))
(def Backdrop (adapt m/Backdrop))
(def Card (adapt m/Card))
(def Menu (adapt m/Menu))
(def FormGroup (adapt m/FormGroup))
(def FormControl (adapt m/FormControl))
(def TextField (adapt m/TextField))
(def InputLabel (adapt m/InputLabel))
(def Input (adapt m/Input))
(def Toolbar (adapt m/Toolbar))
(def Typography (adapt m/Typography))
