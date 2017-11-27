(ns game-of-life.events
  (:require [re-frame.core :as re-frame]
            [game-of-life.db :as db]
            [game-of-life.game :as game]))

(re-frame/reg-event-db
  ::initialize-db
  (fn  [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::step
  (fn [db _]
    (assoc db :cells (game/step db/world (:cells db)))))
