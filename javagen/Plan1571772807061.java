public class Plan1571772807061 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("C");

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");

StartServer("C");

}


}
}
