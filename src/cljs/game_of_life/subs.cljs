(ns game-of-life.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::world
 (fn [db]
   (:world db)))
