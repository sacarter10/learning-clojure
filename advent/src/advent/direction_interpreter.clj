(ns advent.direction-interpreter)

(def directions-file "directions.txt")

(defn parse-file
  "Convert a txt file into list of characters"
  [directions-file]
  (seq (slurp directions-file)))

(def list-of-directions
	(parse-file directions-file)
)

; set of coordinates visited
(def visited-houses
	#{{:x 0 :y 0}})

(def current-position
	{:x 0 :y 0})

; map between directions (<, >, ^, v) and functions
; that will update the current position accordingly
(defn new-current-position [current-pos dir] 
	(cond
		(= dir \^) {:x (get current-pos :x) :y (+ (get current-pos :y) 1)} 
		(= dir \v) {:x (get current-pos :x) :y (- (get current-pos :y) 1)}
		(= dir \<) {:x (- (get current-pos :x) 1) :y (get current-pos :y)}
		(= dir \>) {:x (+ (get current-pos :x) 1) :y (get current-pos :y)}
	)
)

(defn count-visited-houses 
	([] (count-visited-houses list-of-directions visited-houses current-position))
	([directions visited current-pos]
		(if (empty? directions)
			(count visited)
			(let [next-dir (first directions)
						new-current-pos (new-current-position current-pos next-dir)
						new-visited (conj visited new-current-pos)]
				(recur (rest directions) new-visited new-current-pos)
				)))
)




