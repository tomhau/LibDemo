package com.example.libdatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private Connection con; // forbindelsen til databasen
    public BookDaoImpl()  {
        try{
            // Bemærk: selve connection-strengen skal tilpasses Jeres connection settings..

            con = DriverManager.getConnection("jdbc:sqlserver://EASV-THA-Q418\\TH:1433;database=LibDB;userName=sa;password=123456");
            //con = DriverManager.getConnection("jdbc:sqlserver://EASV-THA-Q418\\TH:1433;database=LibDB;integratedSecurity=true");
        } catch (SQLException e){
            System.err.println("can not create connection");
        }

        System.out.println("connected to the database... ");


    }

    public void saveBook(Book book) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO BOOK VALUES(?,?,?);");
            ps.setString(1, book.getIsbn());
            ps.setString(2,book.getTitle());
            ps.setString(3,book.getCategory());
            ps.executeUpdate();

        } catch (SQLException e){
            System.err.println("can not insert record");
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books= new ArrayList();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Book;");
            ResultSet rs = ps.executeQuery();

            Book book;
            while(rs.next()){
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String category = rs.getString(3);

                book = new Book(isbn,title,category);
                books.add(book);
            }

        } catch (SQLException e){
            System.err.println("can not access records");
        }
        return books;
    }
}
