package telran.college.entities;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@Table(name="marks")
public class Mark {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long id;
	@ManyToOne
	@JoinColumn(name="stid", nullable = false)
	Student student;
	@ManyToOne
	@JoinColumn(name="suid", nullable = false)
	Subject subject;
	@Column(nullable = false)
	int score;

}
