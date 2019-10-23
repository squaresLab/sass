public class Plan1571774760308 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


StartServer("A");

}

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
