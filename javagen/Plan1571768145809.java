public class Plan1571768145809 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}


}
}
