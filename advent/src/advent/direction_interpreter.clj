(ns advent.direction-interpreter)

(def directions-file "directions.txt")

(defn parse-file
  "Convert a txt file into list of characters"
  [directions-file]
  (seq (slurp directions-file)))



