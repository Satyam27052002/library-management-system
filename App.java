package libms;
import java.sql.*;
import java.util.Scanner;

public class App 
{
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) 
	{
		while(true)
		{
			System.out.println("\n"+"Select Options:");
			System.out.println("1) Add Book"+"\t \t"+"2) Display All Books"+"\t"+"3) Display Books by Author Name"+"\t \t"+"4) Display Books by Title");
			System.out.println("5) Update Price ID"+"\t"+"6) Update Quantity ID"+"\t"+"7) Delete Book by ID"+"\t \t \t"+"8) Search by Keyword");
			System.out.println("9) Search by Price"+"\t"+"10) Sort by Price Asc"+"\t"+"11) Sort by Price Desc"+"\t \t \t"+"12) Exit");
			int key=s.nextInt();
			switch(key)
			{
			case 1:addBook();
				break;
			case 2:displayBooks();
				break;
			case 3:displayBookByAuthor();
				break;
			case 4:displayBookByTitle();
				break;
			case 5:updatePriceByID();
				break;
			case 6:updateQuantityByID();
				break;
			case 7:deleteBookByID();
				break;
			case 8:searchByKeyword();
				break;
			case 9:searchByPrice();
				break;
			case 10:sortPriceAsc();
				break;
			case 11:sortPriceDesc();
				break;
			case 12:System.exit(0);
				break;
			default:System.out.println("Invalid input");
				break;
			}
		}
	}

	private static void sortPriceDesc() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from book order by price desc");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void sortPriceAsc() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from book order by price asc");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void searchByPrice() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			System.out.println("Enter Price");
			ResultSet rs=st.executeQuery("select * from book where price ='"+s.nextDouble()+"'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void searchByKeyword() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			System.out.println("Enter title");
			ResultSet rs=st.executeQuery("select * from book where title like '"+s.next()+"%'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void updateQuantityByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			PreparedStatement st=con.prepareStatement("update book set quantity=? where  id= ?");
			System.out.println("Enter new quantity");
			st.setDouble(1, s.nextInt());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void deleteBookByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			PreparedStatement st=con.prepareStatement("delete from book where id=?");
			System.out.println("Enter id");
			st.setInt(1, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");
			displayBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void updatePriceByID() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			PreparedStatement st=con.prepareStatement("update book set price=? where  id= ?");
			System.out.println("Enter new price");
			st.setDouble(1, s.nextDouble());
			System.out.println("Enter id");
			st.setInt(2, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void displayBookByTitle() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			System.out.println("Enter Title");
			String title=s.next();
			ResultSet rs=st.executeQuery("select * from book where title='"+title+"'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void displayBookByAuthor() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			System.out.println("Enter Author Name");
			ResultSet rs=st.executeQuery("select * from book where author='"+s.next()+"'");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void displayBooks() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from book");
			System.out.printf("%-5s | %-15s | %-25s | %-15s | %-10s%n", "ID", "Title", "Price", "Author", "Quantity");
		    System.out.println("-----------------------------------------------------------------------------------");
		    while (rs.next()) 
		    {
		       System.out.printf("%-5d | %-15s | %-25s | %-15s | %-10s%n",rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5));
		    }
			rs.close();			
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	private static void addBook() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			PreparedStatement st=con.prepareStatement("insert into book values(?,?,?,?,?)");
			System.out.print("Enter id \t");
			st.setInt(1, s.nextInt());
			System.out.print("Enter title \t");
			st.setString(2, s.next());
			System.out.print("Enter price \t");
			st.setDouble(3, s.nextDouble());
			System.out.print("Enter Author \t");
			st.setString(4, s.next());
			System.out.print("Enter quantity \t");
			st.setInt(5, s.nextInt());
			System.out.println(st.executeUpdate()+" Row updated..."+"\n");	
			displayBooks();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}