(ns game-of-life.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [game-of-life.events :as events]
            [game-of-life.views :as views]
            [game-of-life.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
