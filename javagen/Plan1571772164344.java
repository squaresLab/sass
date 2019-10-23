public class Plan1571772164344 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("B");
}

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



}
}
