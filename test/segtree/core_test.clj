(ns segtree.core-test
  (:require [segtree.core :refer :all]
            [clojure.test :refer :all]))


(defmethod join-vals :sum [a b _] (+ a b))

(defmethod identity-val :sum [_] 0)

(deftest query-sum-test
  (let [arr [1 2 3 4 5 6 7 8 9 10]
        st  (build arr 0 9 :sum)]
    (testing "Query returns expected results"
      (are [a b] (= (query st a b) (apply + (subvec arr a (inc b))))
        0 9
        0 4
        3 9
        2 6))))
