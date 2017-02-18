(ns draw-functions.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn mod-color [ n ] (rem n 256))

(defn mandelbrot [ x y ] 
 (let [ c-real (* 4/500 (- x 250))
        c-imag (* 4/500 (- y 250))
        max-val 10]
   (loop [ n 0 m 0 iter 0 ] 
     (if (or (< 4 (+ (* m m) (* n n))) (>= iter max-val)) (if (>= iter max-val) 0  255  ) 
       (recur (+ c-real (- (* m m) (* n n)))
              (+ c-imag (* 2 m n ))
              (inc iter)))))
)

;  (mandelbrot 250 250)
;  (mandelbrot 0 0)
;  (mandelbrot 250 300)
;  (mandelbrot 500 500)

(defn do-stuff [ x y ] 
  (* (+ x y) (* -1 x) y)) 

(defn divides-by? [ n m ]
  (= 0 (rem n m)))

(def primes #{ 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499 } ) 

(defn prime? [ n ] (contains? primes n))

(defn dist-to-next-prime [n]
      (loop [ dist 1 test-num (inc n) ]
        (cond  
          (prime? test-num) dist
          (>= test-num 500) 0  
          :else (recur (inc dist) (inc test-num)))))

(defn color-by-prime-dist [ x y ] 
  (* 9 
     (+ (dist-to-next-prime x) (dist-to-next-prime y))))

(defn draw [state]
  ;(q/background 0 0 0)

  (doseq [ x (range 500)
           y (range 500) ] 

  (q/stroke  (color-by-prime-dist x y) 0 0)
  (q/point x y))

)


(q/defsketch draw-funcs
  :title "math is a really cool thing"
  :size [500 500]
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
