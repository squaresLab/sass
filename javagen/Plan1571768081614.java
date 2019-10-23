public class Plan1571768081614 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

StartServer("C");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");

}


for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}




}
}
