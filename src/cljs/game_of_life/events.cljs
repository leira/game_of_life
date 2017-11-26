(ns game-of-life.events
  (:require [re-frame.core :as re-frame]
            [game-of-life.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))
