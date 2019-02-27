
package frc.robot;

/*
  Khi tạo project mới để tải lên Raspberry Pi thì cứ tạo một robot project rồi sửa các file trong đấy để được
  ăn sẵn mấy cái thư viện wpilib và opencv và gradle luôn
  Cách upload lên RaspPi:
  - Save tất cả các class để đảm bảo k compile error
  - Mở terminal, gõ .\gradlew clean, chờ build success, gõ .\gradlew build, chờ build success
  - Kết nối với cái cục radio (đã nối cả RPi và RIO) và vào web browser, gõ frcvision.local vào địa chỉ
  - Vào tab Application của dashboard, chọn Uploaded Java program
  - Chọn file .jar ở mục [PROJECT DIRECTORY]\build\libs
  - Save (nhớ chọn option Writable ở trên cùng màn hình)
  - Mở tab Vision Settings
  - Chọn option "Add Other Camera" > Add Switched Camera
  - Nhập tên của OutputStream (mở class Vision ra xem) vào mục name rồi save và nhấn open stream
  - Nó sẽ mở tab mới có stream (đã qua xử lý) về, nếu k được thì cứ refresh vài lần
*/

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.SS_Vision;

public class Main {
  //main class cho những ai chưa quên mất main là gì
  
  //khởi tạo NetworkTables client, sẽ dùng đến sau để đưa các kết quả tính toán về RoboRIO xử lý.
  public static NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
  public static void main(String[] args) {

    System.out.println("Setting up NetworkTables client for team " + 6520);
    ntinst.startClientTeam(6520);

    UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setResolution(640, 360);
    cam.setExposureManual(10);
    
    SS_Vision vision = new SS_Vision();

    while (true){
      vision.process();
    }

  }

}
