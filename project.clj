(defproject segtree "0.1.0-SNAPSHOT"
  :description "A segment tree implementation"
  :url "http://github.com/caioaao/segtree"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot segtree.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
