(ns advent.core)

(def filename "gifts.txt")

(defn parse
  "Convert a txt file into rows of columns"
  [string]
  (map #(clojure.string/split % #"x")
       (clojure.string/split string #"\n")))

(defn mapify [original-list]
	(reduce (fn [new-map [key val]]
)



(defn -main []
 (let [original-list (parse (slurp filename))]
 
 (println original-list)))

