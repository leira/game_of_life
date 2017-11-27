(ns game-of-life.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::cells
 (fn [db]
   (:cells db)))
