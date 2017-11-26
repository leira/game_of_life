(ns game-of-life.views
  (:require [re-frame.core :as re-frame]
            [game-of-life.subs :as subs]
            ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "Hello from " @name]))
