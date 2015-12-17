(ns advent.core
	(:require [advent.direction-interpreter :as di]
						[advent.robot-santa :as robot-santa]
						[advent.wrapping-things :as wrapping-things]))

(defn -main []
; Day 2: how much wrapping paper?
;(println (wrapping-things/total-surface-area list-of-packages))
; Day 2, part 2: how much ribbon?
;(println (wrapping-things/total-ribbon-length list-of-packages))
; Day 3: how many houses get presents?
;(println (di/count-visited-houses))
; Day 3b: how many houses get presents, with the addition of Robot Santa?
(println (robot-santa/count-visited-houses)))

