(ns dodo.models.migration
  (:require [clojure.java.jdbc :as sql]))

(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "db/database.db"})

(defn create-tasks []
  (try (sql/with-connection db
         (sql/create-table :tasks
          [:id :integer "NOT NULL"]
          [:tasker_id :integer "NOT NULL"]
          [:doer_id :integer]
          [:title :varchar "NOT NULL"]
          [:description :varchar "NOT NULL"]
          [:updated_at :timestamp "NOT NULL"]
          [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]
          [:cat_id :integer]))
       (catch Exception e (println e))))

(defn -main []
  (println "Migrating databases ...")
  (flush)
  (create-tasks)
  (println "Done ..."))

(-main)
