public class Plan1571769531502 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

StartServer("C");
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");
StartServer("C");
StartServer("B");



}



for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}


}
}
