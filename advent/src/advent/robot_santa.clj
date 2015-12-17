(ns advent.robot-santa)

(def directions-file "directions.txt")

(defn parse-file
  "Convert a txt file into list of characters"
  [directions-file]
  (seq (slurp directions-file)))

; the even elements fo the directions list (i.e. zero-th item, second item, fourth item etc.)
(def santa-directions
	(take-nth 2 (rest (parse-file directions-file))))

; the odd elements of the directions list (i.e. first item, third item, fifth item  etc.)
(def robot-santa-directions
	(take-nth 2 (parse-file directions-file)))

; set of coordinates visited
(def initial-visited-houses 
	#{{:x 0 :y 0}})

(def starting-position
	{:x 0 :y 0})

; map between directions (<, >, ^, v) and functions
; that will update the current position accordingly
(defn new-current-position [current-pos dir] 
	(cond
		(= dir \^) {:x (get current-pos :x) :y (+ (get current-pos :y) 1)} 
		(= dir \v) {:x (get current-pos :x) :y (- (get current-pos :y) 1)}
		(= dir \<) {:x (- (get current-pos :x) 1) :y (get current-pos :y)}
		(= dir \>) {:x (+ (get current-pos :x) 1) :y (get current-pos :y)}))

(defn map-visited-houses [directions visited current-pos]
	(if (empty? directions)
		visited
		(let [next-dir (first directions)
					new-current-pos (new-current-position current-pos next-dir)
					new-visited (conj visited new-current-pos)]
			(recur (rest directions) new-visited new-current-pos)
			)))


(defn count-visited-houses []
	(let [santas-houses (map-visited-houses santa-directions initial-visited-houses starting-position)
				all-visited-houses (map-visited-houses robot-santa-directions santas-houses starting-position)]
		(count all-visited-houses)))






