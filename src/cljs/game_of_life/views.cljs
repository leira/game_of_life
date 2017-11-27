(ns game-of-life.views
  (:require [re-frame.core :as re-frame]
            [game-of-life.subs :as subs]
            [game-of-life.game :as game]))

(defn cell [x y color]
  [:rect {:x x
          :y y
          :width 10
          :height 10
;          :stroke "black"
;          :stroke-width 0.01
;          :rx 0.1
          :fill color}])

(defn grid []
  (let [world (re-frame/subscribe [::subs/world])
        board [:svg {:width "100%" :height "100%"}
               [:defs
                [:pattern {:id "smallGrid" :width "10" :height "10" :patternUnits "userSpaceOnUse"}
                 [:path {:d "M 10 0 L 0 0 0 10" :fill "none" :stroke "gray" :stroke-width "0.5"}]]
                [:pattern {:id "grid" :width "100" :height "100" :patternUnits "userSpaceOnUse"}
                  [:rect {:width "100" :height "100" :fill "url(#smallGrid)"}]
                  [:path {:d "M 100 0 L 0 0 0 100" :fill "none" :stroke "gray" :stroke-width "1"}]]]
               [:rect {:width "100%" :height "100%" :fill "url(#grid)"}]]]
;               [cell 30 20 "red"]
;               [cell 40 20 "green"]]]
    (into board
          (->> @world
              (game/map-indexed-2d vector)
              (apply concat)
              (filter #(pos? (second %)))
              (map (fn [[[y x] _]] [cell (* 10 x) (* 10 y) "black"]))))
    )
)

(defn main-panel []
  [:div {:style {:width "801px" :height "801px"}} [grid]]
;  (let [name (re-frame/subscribe [::subs/name])]
;    [:div "Hello from " @name]))
)
