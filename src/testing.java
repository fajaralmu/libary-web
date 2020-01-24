import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;

import com.fajar.DAO.DAOBuku;
import com.fajar.DAO.DAOPost;
import com.fajar.DAO.DAOUser;
import com.fajar.Model.Buku;
import com.fajar.Model.Post;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DAOBuku dob = new DAOBuku();
		DAOUser dou = new DAOUser();
		// Buku b = new Buku("b5", "geografi", "fajar", "fajr press", 44000,
		// 30);
		DAOPost dop = new DAOPost();

		Post p = new Post("judul revisi", "konten", 1, 3, null, "katakunci", 2);
		p.setIdpost(1);
		Buku b2 = new Buku();
		b2.setId(1);
		try {
			List<Post> p2 = dop.dapatkanPost("idpengguna", "3");
			System.out.println(p2);
			for(Post p1:p2){
				System.out.println(p1.toString());
			}
			/*
			 * if(dop.tambahPost(p)){ System.out.println("data masuk"); }
			 */
			/*
			 * if(dop.editPost(p)){ System.out.println("data update"); }
			 */
			// for(Post p2: dop.daftarPost()){
			// System.out.println(p2.toString());
			// }
			//System.out.println(dop.dapatkanPost("judul", "d").toString());
			// System.out.println(dob.dapatkanBuku("judul",
			// "biologi").toString());
			// System.out.println(dou.cariUser("username", "ali").toString());
			/*
			 * if(dob.tambahBuku(b)){ System.out.println("data tersimpan"); }
			 */
			/*
			 * for(Buku buku: dob.daftarBuku()){
			 * System.out.println(buku.toString()); }
			 */
//			System.out.println(Jsoup.parse(
//					"<font color='#0000ff'>assalamu</font><div><span style='background-color: red;'><font face='Arial Black' color='#008000'>alaikum</font></span></div>")
//					.text());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
