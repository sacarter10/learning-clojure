(ns advent.core
	(:require [advent.direction-interpreter :as di]))

(def filename "gifts.txt")

(defn parse
  "Convert a txt file into rows of columns"
  [string]
  (map #(clojure.string/split % #"x")
       (clojure.string/split string #"\n")))

(defn str->int
  [str]
  (Integer. str))

(def dimension-keys
	[:length :width :height])

(defn two-dimensional-array-to-map [row-map [myKey myVal]]
	(assoc row-map myKey (str->int myVal)))

(defn do-this-to-each-row [measurement-row]
	(reduce two-dimensional-array-to-map {} (map vector dimension-keys measurement-row))
)

(defn mapify [original-list]
 ; I have a list of vectors ([24 11 19] [4 5 6])
 (map do-this-to-each-row original-list)
 ; now I have a list of maps ({:height 24, :width 11, :length 3} {:height 4, :width 5, :length 6})
)

(defn area-of-smallest-side [height width length]
	(let [largest-side (max height width length)]
		(/ (* height width length) largest-side)
	)
)

; 2*l*w + 2*w*h + 2*h*l + area of smallest side
(defn surface-area [map-of-dimensions]
	(let [height (get map-of-dimensions :height)
				width (get map-of-dimensions :width)
				length (get map-of-dimensions :length)
				smallest-area (area-of-smallest-side height width length)]		

	(+ (+ (* 2 length width) (* 2 width height) (* 2 height length)) smallest-area)))

(defn total-surface-area [list-of-packages]
	(reduce + (map surface-area list-of-packages))
)

(defn cubic-volume [map-of-dimensions]
	(let [height (get map-of-dimensions :height)
				width (get map-of-dimensions :width)
				length (get map-of-dimensions :length)]

	(* height width length)
))

(defn smallest-face-perimeter [map-of-dimensions]
	(let [height (get map-of-dimensions :height)
				width (get map-of-dimensions :width)
				length (get map-of-dimensions :length)
				largest-side (max height width length)]

	(- (+ (* 2 height) (* 2 width) (* 2 length)) (* 2 largest-side))
))

; perimeter of smallest face + cubic volume of present
(defn ribbon-length [map-of-dimensions]
	(+ (smallest-face-perimeter map-of-dimensions) (cubic-volume map-of-dimensions))
)

(defn total-ribbon-length [list-of-packages]
	(reduce + (map ribbon-length list-of-packages))
)


(defn -main []
 (let [original-list (parse (slurp filename))
 			 list-of-packages (mapify original-list)]

; Day 2: how much wrapping paper?
(println (total-surface-area list-of-packages))
; Day 2, part 2: how much ribbon?
(println (total-ribbon-length list-of-packages))
; Day 3: how many houses get presents?
))

