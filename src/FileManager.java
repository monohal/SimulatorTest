import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	File file;


	/**
	 * コンストラクタ
	 * 引数が無ければ何もしない
	 */
	public FileManager(){
	}

	/**
	 * コンストラクタ
	 * fileName.csvでファイルを作成する
	 * @param fileName
	 */

	public FileManager(String fileName) {
		createFile(fileName);
		}

	/**
	 * fileNameという名前のcsvファイルが存在しなければ作成する
	 * @param fileName
	 */
	public void createFile(String fileName){
		try{
			this.file = new File(fileName + ".csv");
			if(!this.file.exists()){
				this.file.createNewFile();

				FileWriter fw = new FileWriter(this.file);
				fw.write( "xPoint" + ",");
				fw.write( "Lmin" + ",");
				fw.write( "AXB" + ",");
				fw.write( "BXC" + ",");
				fw.write( "CXA" + "," + "\n");
				fw.close();
			}
		}catch(IOException e){
			System.out.println(e + "ファイル作成に失敗しました。");
		}

	}

	/**
	 * paramを書き込む
	 * @param xPoint
	 * @param Lmin
	 * @param angleAXB
	 * @param angleBXC
	 * @param angleCXA
	 */
	public void saveData( Point xPoint, double Lmin,
					double angleAXB, double angleBXC, double angleCXA){
		try{
			FileWriter fw = new FileWriter(this.file, true);
			fw.write( xPoint + ",");
			fw.write( Lmin + ",");
			fw.write( angleAXB + ",");
			fw.write( angleBXC + ",");
			fw.write( angleCXA + "," + "\n");
			fw.close();
		}catch(IOException e){
			System.out.println(e + "例外が発生");
		}
	}
}
