public class Plan1571773632185 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");

}

StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}


for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
