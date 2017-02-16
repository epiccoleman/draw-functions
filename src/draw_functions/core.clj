(ns draw-functions.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn mod-color [ n ] (rem n 256))

(defn do-stuff [ x y ] 
  (* (+ x y) (* -1 x) y)) 

(defn draw [state]
  (q/background 0 0 0)

  (doseq [ x (range 500)
           y (range 500) ] 

  (q/stroke (mod-color (do-stuff x y)))
  (q/point x y))

)

(q/defsketch draw-funcs
  :title "math is a really cool thing"
  :size [500 500]
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
