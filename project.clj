(defproject com.flyingmachine/datomic-junk "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.flyingmachine/config "2.0.0"]
                 [environ "0.4.0"]]
  :profiles {:dev {:dependencies [[com.datomic/datomic-free "0.9.4360"]
                                  [midje "1.5.0"]]
                   :env {:datomic {:db-uri "datomic:mem://datomic-junk-test"}}}})
