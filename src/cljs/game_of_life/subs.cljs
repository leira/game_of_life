(ns game-of-life.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::cells
 (fn [db]
   (:cells db)))

(re-frame/reg-sub
 ::started
 (fn [db]
   (:started db)))

(re-frame/reg-sub
 ::steps
 (fn [db]
   (:steps db)))
