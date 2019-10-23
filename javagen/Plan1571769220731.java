public class Plan1571769220731 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



StartServer("B");

} else {
StartServer("B");
}

}

}
}
