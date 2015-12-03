(defn mapset [fun coll]         	
 (loop [result #{}
 				current (first coll)
        remaining (rest coll)]

   (if (empty? remaining)
     result
     (recur (conj result (fun current))
     				(first remaining)
            (rest remaining)))))


