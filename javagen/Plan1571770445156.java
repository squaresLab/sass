public class Plan1571770445156 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("C");
StartServer("C");
StartServer("B");


StartServer("A");

DecreaseTraffic("A");
StartServer("B");


StartServer("A");


}

}
}
