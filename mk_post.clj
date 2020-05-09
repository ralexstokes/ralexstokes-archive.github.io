#!/usr/bin/env bb

(ns mk-jekyll-post
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def posts-dir "_posts")
(def drafts-dir "_drafts")
(def LA-timezone (java.time.ZoneId/of "America/Los_Angeles"))

(defn- pattern-for [type]
  (get
   {:title java.time.format.DateTimeFormatter/ISO_LOCAL_DATE
    :front-matter (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd kk:mm Z")}
   type))

(defn- load-date []
  (-> (java.time.ZonedDateTime/now)
      (.withZoneSameInstant LA-timezone)))

(defn- read-title []
  (if-let [title (first *command-line-args*)]
    title
    (throw (ex-info "" {:error "missing title"}))))

(defn- title->file-name [date title]
  (str (.format date (pattern-for :title))
       "-"
       (str/join "-" (str/split (str/lower-case title) #"\s"))
       ".md"))

(defn- mk-file [date title]
  (->> title
      (title->file-name date)
      (str drafts-dir "/")
      io/file))

(defn- mk-front-matter-date [date]
  (.format date (pattern-for :front-matter)))

(defn- mk-front-matter [title date]
  ["---"
   "layout: post"
   (str "title: \"" title "\"")
   (str "date: " (mk-front-matter-date date))
   "---\n"])

(defn- -main []
  (let [date (load-date)
        title (read-title)
        file (mk-file date title)
        front-matter (mk-front-matter title date)]
    (io/make-parents file)
    (spit file (str/join "\n" front-matter))
    (.getName file)))

(-main)
