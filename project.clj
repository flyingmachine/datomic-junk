(defproject com.flyingmachine/datomic-junk "0.2.3"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[com.datomic/datomic-free "0.9.4360"]
                                  [org.clojure/tools.namespace "0.2.5"]
                                  [midje "1.5.0"]]}})
