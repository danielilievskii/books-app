import { useState } from 'react'
import './App.css'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {Header} from "./ui/components/layout/Header/Header.jsx";
import {HomePage} from "./ui/pages/HomePage/HomePage.jsx";
import {BooksPage} from "./ui/pages/BooksPage/BooksPage.jsx";
import {CountriesPage} from "./ui/pages/CountriesPage/CountriesPage.jsx";
import {AuthorsPage} from "./ui/pages/AuthorsPage/AuthorsPage.jsx";
import SignInPage from "./ui/pages/SignInPage/SignInPage.jsx";
import {BookDetailsPage} from "./ui/pages/BookDetailsPage/BookDetailsPage.jsx";

function App() {

  return (
      <BrowserRouter>
          <Header />
          <Routes>
              <Route index element={<HomePage/>}></Route>
              <Route path={"/books"} element={<BooksPage/>}></Route>
              <Route path={"/books/:id"} element={<BookDetailsPage/>}></Route>
              <Route path={"/countries"} element={<CountriesPage/>}></Route>
              <Route path={"/authors"} element={<AuthorsPage/>}></Route>
              <Route path={"/signin"} element={<SignInPage/>}></Route>
          </Routes>
      </BrowserRouter>
  )
}

export default App
