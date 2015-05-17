(defproject dodo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.2.0"]
                 [compojure "1.1.3" :exclude [clout]]
                 [org.clojure/java.jdbc "0.3.0-alpha5"]
                 [org.xerial/sqlite-jdbc "3.7.15-M1"]
                 [ring-basic-authentication "1.0.2"]
                 [enlive "1.1.1"]
                 [clout "1.1.0"]]
  :plugins [[lein-ring "0.8.6"]]
  :ring {:handler dodo.core/app})
