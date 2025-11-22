(ns suchcab-app.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::current-view
 (fn [db]
   (:current-view db)))

(re-frame/reg-sub
 ::user
 (fn [db]
   (:user db)))

(re-frame/reg-sub
 ::loading
 (fn [db]
   (:loading db)))

(re-frame/reg-sub
 ::error
 (fn [db]
   (:error db)))

(re-frame/reg-sub
 ::success
 (fn [db]
   (:success db)))

(re-frame/reg-sub
 ::form-errors
 (fn [db]
   (:form-errors db)))
