public class Plan1571768167859 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
DecreaseTraffic("A");

}




}
}
