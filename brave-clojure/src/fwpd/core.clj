(ns fwpd.core)

(def filename "suspects.csv")

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))



(def vamp-keys [:name :glitter-index])

(defn make-list-of-pairs-into-Map [row-map [vamp-key value]]
  (assoc row-map vamp-key (convert vamp-key value))
)

(defn do-this-to-each-row [unmapped-row]
  (reduce make-list-of-pairs-into-Map {} (map vector vamp-keys unmapped-row))
)

; "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
(defn mapify [rows]
  (map do-this-to-each-row rows))

(defn validate-suspect [new-suspect]
  true
)

(defn append-suspect [suspect-list new-suspect]
  (if (validate-suspect new-suspect)
  (conj suspect-list new-suspect)
  (println "your suspect is invalid")))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(defn vampire-names [vampire-list]
  (map :name vampire-list))

(defn -main []
 (let [original-list (mapify (parse (slurp filename)))
       new-suspect { :name "Dracula" 
                     :glitter-index 10}
       final-list (append-suspect original-list new-suspect)]

  (println "mapified")
  (println original-list)
 
 (println (vampire-names (glitter-filter 3 final-list)))))

