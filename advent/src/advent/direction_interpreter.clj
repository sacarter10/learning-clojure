(ns advent.direction-interpreter)

(def directions-file "directions.txt")

(defn parse-file
  "Convert a txt file into list of characters"
  [directions-file]
  (seq (slurp directions-file)))

; set of coordinates visited
(def visited
	#{{:x 0 :y 0}})

(def current-position
	{:x 0 :y 0})

; map between directions (<, >, ^, v) and functions
; that will update the current position accordingly
(def update-current-position-functions 
	{
		"^" #({:x (:x %) :y (+ (:y %) 1)}) 
		"v" (fn [current-pos] {:x (:x current-pos) :y (- (:y current-pos) 1)})
		"<" (fn [current-pos] {:x (- (:x current-pos) 1) :y (:y current-pos) })
		">" (fn [current-pos] {:x (+ (:x current-pos) 1) :y (+ (:y current-pos) 1)})
	}
)

(defn count-visited-houses 
	([] (count-visited-houses list-of-directions visited current-position))
	([list-of-directions visited current-position]
		(let [next-dir (first list-of-directions)]
			(println (get update-current-position-functions next-dir) current-position)))
)

; get directions in a list / vector
; loop through the list of directions
; for each direction: 
; 	update current position
; 	add current position to visited set


