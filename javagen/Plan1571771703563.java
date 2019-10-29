public class Plan1571771703563 extends Plan { 
public static void main(String[] args) { 
if ( IncreaseTraffic("A") ) {
StartServer("B");
} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
IncreaseTraffic("B");

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}



}
}
