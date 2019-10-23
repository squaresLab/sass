public class Plan1571771569342 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



}
}
