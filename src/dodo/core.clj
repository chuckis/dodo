(ns dodo.core
  (:use compojure.core)
  (:require [ring.middleware.params :as params]
            [ring.util.response :as response]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [net.cgrand.enlive-html :as html]
            [dodo.models.tasks :as tasks]))

                                        ;layouts===============

(def nav-items
  {"Home" "/"
   "Все задания" "/alltasks"
   "исполнители" "/bunch"})

(html/defsnippet header "templates/header.html"
  [:body :div.navbar]
  [current-path]
  [:a.brand] (html/content "Dodo - local tasks")
  [:ul.nav [:li html/first-of-type]] (html/clone-for [[caption url] nav-items]
                                                     [:li] (if (= current-path url)
                                                             (html/set-attr :class "active")
                                                             identity)
                                                     [:li :a] (html/content caption)
                                                     [:li :a] (html/set-attr :href url)))

(html/deftemplate main-template "templates/application.html"
  [{:keys [path]}]
  [:head :title] (html/content "DOLOTO")
  [:body] (html/do-> (html/append (header path))))

                                        ;TODO надо разобраться с энлив а то никак чото пока...

(html/deftemplate all-tasks-page "templates/alltasks.html"
  [tasksall]
  [:head :title] (html/content "DOLOTO")
  ([:li] (html/clone-for [task tasksall] (html/content task))))

(defn index-page
  [request]
  (main-template {:path (:uri request)}))

(defn all-tasks
  [request]
  (all-tasks-page tasks/all))

(defroutes main-routes
  (GET "/" request (index-page request))
  (GET "/alltasks" request (all-tasks request))
  (route/not-found "ups, page not found"))

(def app
  (-> (handler/site main-routes)))
