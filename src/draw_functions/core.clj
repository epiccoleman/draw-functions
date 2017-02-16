(ns draw-functions.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn draw [state]
  (q/background 0 0 0)

  (doseq [ x (range 500)
           y (range 500) ] 
  (q/stroke (rem (bit-xor x y) 256))
  (q/point x y)
  
  )
)

(q/defsketch draw-funcs
  :title "math is a really cool thing"
  :size [500 500]
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
